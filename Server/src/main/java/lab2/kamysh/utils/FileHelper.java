package lab2.kamysh.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {

    private static final String SOLVE_FILE = System.getenv("SOLVE_FILE");

    private static String getNextId() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(SOLVE_FILE));
        String last = "1", line;

        while ((line = input.readLine()) != null) {
            last = line;
        }
        last.replace(".","");
        int nextId = Integer.parseInt(last);
        return ++nextId + ".";
    }

    public static String getSolveById(String id) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(SOLVE_FILE));
        String line = "null";

        while (!id.equals(line.charAt(0))) {
                line = input.readLine();
            if (line == null) return "not found";

        }
        return line;
    }

    public static void saveSolution(String solution) {
        try {
            FileWriter writer = new FileWriter(SOLVE_FILE, false);
            assert solution != null;
            writer.write(solution);
            writer.append('\n');
            writer.write(getNextId());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ServerCode.ERROR_WRITING);
        }
    }
}
