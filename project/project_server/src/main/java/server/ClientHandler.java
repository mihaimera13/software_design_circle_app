package server;

import server.image.ProxyImageServer;
import server.services.*;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ClientHandler implements Runnable{

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private AccountService accountService;
    private AdminService adminService;

    private LoginService loginService;
    private NewAccountService newAccountService;
    private TestService testService;
    private LanguageService languageService;
    private ProxyImageServer proxyImageServer;
    public ClientHandler(Socket socket) {

        accountService = new AccountService();
        adminService = new AdminService();
        loginService = new LoginService();
        newAccountService = new NewAccountService();
        testService = new TestService();
        languageService = new LanguageService();
        proxyImageServer = new ProxyImageServer();

        try{
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void broadcastMessage(String message) {

        System.out.println(message);

        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            closeAll(socket, bufferedReader, bufferedWriter);
        }

    }

    @Override
    public void run() {
        String current_message="";
        String previous_message="";

        while(socket.isConnected()){
            try {

                current_message = bufferedReader.readLine();

                if(current_message.equals(previous_message)){
                    continue;
                }
                previous_message = current_message;

                System.out.println(current_message);

                String[] messageParts = current_message.split(" ");

                String command = messageParts[0];

                String result;

                switch (command) {
                    case "GET_TESTS" -> {
                        result = accountService.retrieveTests(messageParts[1]);
                        broadcastMessage(result);
                    }
                    case "GET_ID" -> {
                        result = accountService.getId(messageParts[1]);
                        broadcastMessage(result);
                    }
                    case "DELETE_STUDENT" -> {
                        result = adminService.deleteStudent(messageParts[1]);
                        broadcastMessage(result);
                    }
                    case "APPROVE_STUDENT" -> {
                        result = adminService.approveStudent(messageParts[1]);
                        broadcastMessage(result);
                    }
                    case "SEE_ALL_STUDENTS" -> {
                        result = adminService.seeAllStudents();
                        broadcastMessage(result);
                    }
                    case "SEE_REQUESTS" -> {
                        result = adminService.seeRequests();
                        broadcastMessage(result);
                    }
                    case "LOGIN" -> {
                        result = loginService.login(messageParts[1]);
                        broadcastMessage(result);
                    }
                    case "NEWACCOUNT" -> {
                        result = newAccountService.newAccount(messageParts[1], messageParts[2], messageParts[3], messageParts[4]);
                        broadcastMessage(result);
                    }
                    case "INSERT_TEST" ->
                        testService.insertTest(messageParts[1],messageParts[2],messageParts[3]+" "+messageParts[4]);

                    case "UPDATE_LANG" -> {
                        int index = Integer.parseInt(messageParts[1]);
                        System.out.println(index);
                        languageService.updateLang(index);
                    }
                    case "GET_QUESTIONS" -> {
                        StringBuilder sb = new StringBuilder();
                        for (int i = 1; i < messageParts.length; i++) {
                            sb.append(messageParts[i]).append(" ");
                        }

                        result = testService.getQuestions(sb.toString());
                        broadcastMessage(result);
                    }
                    case "GET_IMAGE" -> {
                        result = Arrays.toString(proxyImageServer.getImage(messageParts[1]));
                        broadcastMessage(result);
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void closeAll(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
