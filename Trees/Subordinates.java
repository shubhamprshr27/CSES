//package Trees;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FastReader {
    final private int BUFFER_SIZE = 1 << 16;
    private DataInputStream din;
    private byte[] buffer;
    private int bufferPointer, bytesRead;

    public FastReader()
    {
        din = new DataInputStream(System.in);
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public FastReader(String file_name) throws IOException
    {
        din = new DataInputStream(
            new FileInputStream(file_name));
        buffer = new byte[BUFFER_SIZE];
        bufferPointer = bytesRead = 0;
    }

    public String readLine() throws IOException
    {
        byte[] buf = new byte[64]; // line length
        int cnt = 0, c;
        while ((c = read()) != -1) {
            if (c == '\n') {
                if (cnt != 0) {
                    break;
                }
                else {
                    continue;
                }
            }
            buf[cnt++] = (byte)c;
        }
        return new String(buf, 0, cnt);
    }

    public int nextInt() throws IOException
    {
        int ret = 0;
        byte c = read();
        while (c <= ' ') {
            c = read();
        }
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (neg)
            return -ret;
        return ret;
    }

    public long nextLong() throws IOException
    {
        long ret = 0;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();
        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        if (neg)
            return -ret;
        return ret;
    }

    public double nextDouble() throws IOException
    {
        double ret = 0, div = 1;
        byte c = read();
        while (c <= ' ')
            c = read();
        boolean neg = (c == '-');
        if (neg)
            c = read();

        do {
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');

        if (c == '.') {
            while ((c = read()) >= '0' && c <= '9') {
                ret += (c - '0') / (div *= 10);
            }
        }

        if (neg)
            return -ret;
        return ret;
    }

    private void fillBuffer() throws IOException
    {
        bytesRead = din.read(buffer, bufferPointer = 0,
                             BUFFER_SIZE);
        if (bytesRead == -1)
            buffer[0] = -1;
    }

    private byte read() throws IOException
    {
        if (bufferPointer == bytesRead)
            fillBuffer();
        return buffer[bufferPointer++];
    }

    public void close() throws IOException
    {
        if (din == null)
            return;
        din.close();
    }
}

public class Subordinates {
    
    static int [] ans;
    static List<List<Integer>> input;
    public static void findSubordinates(List<Integer> level, int parent)
    {
        ans[parent]=1;
        for(int i=0;i<level.size();i++){
            int currLevel = level.get(i);
            findSubordinates(input.get(currLevel), currLevel);
            ans[parent]+=ans[currLevel];
        }
    }
    
    public static void main(String[] args) throws IOException {
        FastReader f = new FastReader();
        int n = f.nextInt();
        input = new ArrayList<>();
        for(int i=0;i<n;i++)input.add(new ArrayList<>());
        //List<Integer> a;
        ans = new int[n];
        for(int i=2;i<=n;i++){
            int v = f.nextInt();
            input.get(v-1).add(i-1);
        }
        findSubordinates(input.get(0),0);
        // Using string buffer to append each output in a string
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++)
            sb.append(ans[i]-1 + " ");
        // finally printing the string
        System.out.println(sb);
        //System.out.println(Arrays.toString(ans));
    }
}