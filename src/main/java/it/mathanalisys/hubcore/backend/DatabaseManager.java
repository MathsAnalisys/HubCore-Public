package it.mathanalisys.hubcore.backend;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import it.mathanalisys.hubcore.backend.data.HubData;
import it.mathanalisys.hubcore.HubCore;
import lombok.Getter;
import org.bson.Document;

import java.util.UUID;

@Getter
public class DatabaseManager {

    @Getter
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> players;


    public DatabaseManager(){
        this.client = new MongoClient(new ServerAddress(HubCore.get().getConfig().getString("Database.ip"),
                HubCore.get().getConfig().getInt("Database.port")));
        this.database = client.getDatabase("hubcore");
        this.players = database.getCollection("players");
    }

    public Document getPlayerDocument(UUID uuid){
        return this.players.find(Filters.eq("uuid", uuid.toString())).first();
    }

    //Fix for double uuid maker()
    public void replacePlayerDocument(HubData hubData, Document document){
        this.players.replaceOne(Filters.eq("uuid", hubData.getUuid().toString()), document, new ReplaceOptions().upsert(true));
    }
}
