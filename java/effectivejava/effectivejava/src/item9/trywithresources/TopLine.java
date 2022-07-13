package item9.trywithresources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TopLine {
    // 코드 9-3 try-with-resources - 자원을 회수하는 최선책! (48 page)
    static String firstLineOfFile(String path) throws IOException {
        // BufferedReader 는 Closeable => autoClosable 로 구현되어 있다.
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    public static void main(String[] args) throws IOException{
        String path = args[0];
        System.out.println(firstLineOfFile(path));

    }
}
