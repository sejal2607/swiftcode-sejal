package data;

public class Message {
    public String text;
      public Sender sender;
    public enum Sender {
        BOT, USER
    }
    public FeedResponse feedResponse;
}
