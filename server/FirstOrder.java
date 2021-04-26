package nio.server;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static nio.server.NeoBASHcmd.err;
import static nio.server.NeoBASHcmd.values;

public class FirstOrder implements HandlerCommand {
    private String rawString;
    private Path currentPath;

    public FirstOrder(Path INIT_PATH) {
        this.currentPath = INIT_PATH;
    }

    @Override
    public String execute(String rawString) {
        if (rawString.equals("\r\n"))
            return currentPath + " >> ";
        this.rawString = rawString.trim();
        Pair<NeoBASHcmd, String> pair = getCommand();
        return switchCommand(pair);
    }

    private Pair<NeoBASHcmd, String> getCommand() {
        String[] tmpStr;
        if (!rawString.contains(" "))
            tmpStr = new String[]{rawString, "crutch"};
        else
            tmpStr = rawString.split(" ", 2);
        for (NeoBASHcmd item :
                values()) {
            if (item.name().equals(tmpStr[0]) && item != err)
                return new Pair<>(item, tmpStr[1]);
        }
        return new Pair<>(err, String.format("NeoBASH: %s: command not found\n", tmpStr[0]));
    }

    private String switchCommand(Pair<NeoBASHcmd, String> pair) {
        switch (pair.getKey()) {
            case cd:
                if (navigate(pair.getValue()))
                    return String.format("%s >> ", currentPath);
                else
                    return String.format("%s >> not found directory %s\n%s >> ", currentPath, pair.getValue(), currentPath);
            case ls:
                return directoryView();
            case cat:
                return viewFile(pair.getValue());
            case mkdir:
                if (createFolder(pair.getValue()))
                    return String.format("%s >> ", currentPath);
                else
                    return String.format("%s >> Can't create folder %s\n%s >> ", currentPath, pair.getValue(), currentPath);
            case touch:
                if (createFile(pair.getValue()))
                    return String.format("%s >> ", currentPath);
                else
                    return String.format("%s >> Can't create file: %s\n%s >> ", currentPath, pair.getValue(), currentPath);
            case exit:
                return "close NeoBASH";
            default:
                return String.format("%s >> %s%s >> ", currentPath, pair.getValue(), currentPath);
        }
    }

    private String directoryView() {
        StringBuilder sb = new StringBuilder();
        List<String> listNameFiles = listFiles();
        for (String name :
                listNameFiles) {
            String tmp = name.substring(name.lastIndexOf("/") + 1);
            sb.append(tmp).append("\n");
        }
        sb.append(currentPath).append(" >> ");
        return sb.toString();
    }

    private boolean createFile(String value) {
        Path newFile = Paths.get(currentPath.toString(), value).toAbsolutePath();
        try {
            Files.createFile(newFile);
            return true;
        } catch (IOException e) {
            System.err.printf("Can't create file %s: %s", value, e);
        }
        return false;
    }

    private boolean createFolder(String value) {
        Path newDir = Paths.get(currentPath.toString(), value).toAbsolutePath();
        try {
            Files.createDirectory(newDir);
            return true;
        } catch (IOException e) {
            System.err.println("Fail create directory: " + e);
        }
        return false;
    }

    private String viewFile(String value) {
        List<String> list = listFiles();
        File file = new File(Paths.get(currentPath.toString(), value).toAbsolutePath().toUri());
        if (!list.contains(file.getAbsolutePath()))
            return String.format("%s >> not found file %s\n%s >> ", currentPath, value, currentPath);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file)
        )) {
            String tmpStr;
            while ((tmpStr = reader.readLine()) != null)
                sb.append(tmpStr).append("\n");
        } catch (IOException e) {
            sb.append(String.format("Fail open file: %s\n%s", value, e));
            System.err.println(sb);
        }
        sb.append(currentPath).append(" >> ");
        return sb.toString();
    }

    private List<String> listFiles() {
        List<String> filesList = new ArrayList<>();
        try {
            Files.walkFileTree(currentPath,
                    new HashSet<>(Arrays.asList(FileVisitOption.values())),
                    1,
                    new SimpleFileVisitor<Path>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            filesList.add(file.toString());
                            return super.visitFile(file, attrs);
                        }
                    });
        } catch (IOException e) {
            System.err.println("Fail read files tree " + e);
        }
        return filesList;
    }

    private boolean navigate(String value) {
        if (value.equals("..")) {
            if (currentPath.toString().equals("/"))
                return false;
            currentPath = currentPath.getParent();
            return true;
        }
        Path foundPath = Paths.get(currentPath.toString(), value);
        List<String> listFiles = listFiles();
        if (listFiles.contains(foundPath.toString())) {
            currentPath = foundPath;
            return true;
        } else
            return false;
    }
}
