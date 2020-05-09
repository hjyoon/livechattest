package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ChatDAO {
    private Connection conn;

    public ChatDAO() {
        try {
            Class.forName("org.postgresql.Driver");
            String dbURL = "jdbc:postgresql://localhost:";
            String dbID = "";
            String dbPassword = "";
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Chat> getChatList(String nowTime) {
        ArrayList<Chat> chatList = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM public.CHAT WHERE chatTime > ? ORDER BY chatTime";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, nowTime);
            rs = pstmt.executeQuery();
            chatList = new ArrayList<Chat>();
            while(rs.next()) {
                Chat chat = new Chat();
                chat.setChatID(rs.getInt("chatID"));
                chat.setChatName(rs.getString("chatName"));
                chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;")
                        .replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
                chat.setChatTime(rs.getString("chatTime"));
                chatList.add(chat);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chatList;
    }

    public ArrayList<Chat> getChatListByRecent(int number) {
        ArrayList<Chat> chatList = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM public.CHAT WHERE chatID > (SELECT MAX(chatID) - ? FROM public.CHAT) ORDER BY chatTime";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, number);
            rs = pstmt.executeQuery();
            chatList = new ArrayList<Chat>();
            while(rs.next()) {
                Chat chat = new Chat();
                chat.setChatID(rs.getInt("chatID"));
                chat.setChatName(rs.getString("chatName"));
                chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;")
                        .replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
                chat.setChatTime(rs.getString("chatTime"));
                chatList.add(chat);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chatList;
    }

    public ArrayList<Chat> getChatListByRecent(String chatID) {
        ArrayList<Chat> chatList = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM public.CHAT WHERE chatID > ? ORDER BY chatTime";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, Integer.parseInt(chatID));
            rs = pstmt.executeQuery();
            chatList = new ArrayList<Chat>();
            while(rs.next()) {
                Chat chat = new Chat();
                chat.setChatID(rs.getInt("chatID"));
                chat.setChatName(rs.getString("chatName"));
                chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;")
                        .replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
                chat.setChatTime(rs.getString("chatTime"));
                chatList.add(chat);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return chatList;
    }

    public int submit(String chatName, String chatContent) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String SQL = "INSERT INTO public.CHAT(chatname, chatcontent, chattime) VALUES (?, ?, now())";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, chatName);
            pstmt.setString(2, chatContent);
            return pstmt.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }
}
