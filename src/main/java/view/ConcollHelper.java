package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConcollHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static void writeMessage(String message) {
        System.out.println(message);
    }

    static int readInt() throws IOException {
        return Integer.valueOf(reader.readLine());
    }
    static String readString() throws IOException {
        return reader.readLine();
    }
}
