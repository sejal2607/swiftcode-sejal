package actors;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.FeedResponse;
import data.Message;
import data.NewsAgentResponse;
import services.FeedService;
import services.NewsAgentService;

import java.util.UUID;


public class MessageActor extends UntypedActor {


    private final ActorRef out;
    private FeedService feedService = new FeedService();
    private NewsAgentService newsAgentService = new NewsAgentService();
    private FeedResponse feedresponse = new FeedResponse();

    public static Props props(ActorRef out) {
        return Props.create(MessageActor.class, out);
    }

    public MessageActor(ActorRef out) {
        this.out = out;
    }


    @Override
    public void onReceive(Object message) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        if (message instanceof String) {
            Message messageobject = new Message();
            messageobject.text=(String)message;
            messageobject.sender=Message.Sender.USER;
            out.tell(objectMapper.writeValueAsString(messageobject), self());

            //String query=newsAgentService.getNewsAgentResponse(messageobject.text,UUID.randomUUID() ).query;
            NewsAgentResponse newsAgentResponse=newsAgentService.getNewsAgentResponse(messageobject.text,UUID.randomUUID() );//dumping in query
            FeedResponse feedResponse=feedService.getFeedByQuery(newsAgentResponse.query);//newsAgent Responce query to fetch the keyword which is stored in the newsAgentResponce.
            messageobject.text = (feedResponse.title == null) ? "No results found" : "Showing results for: " + newsAgentResponse.query;
            messageobject.feedResponse=feedResponse;
            messageobject.sender=Message.Sender.BOT;
            out.tell(objectMapper.writeValueAsString(messageobject), self());



        }

    }


}