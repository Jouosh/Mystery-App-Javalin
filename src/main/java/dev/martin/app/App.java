package dev.martin.app;

import com.google.gson.Gson;
import dev.martin.dtos.Coordinate;
import dev.martin.dtos.IndexedNote;
import dev.martin.dtos.NamedDocument;
import io.javalin.Javalin;

import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.*;

public class App {

    static List<String> notes = new ArrayList();

    public static void main(String[] args) {

        Javalin app = Javalin.create();

        //Default route
        app.get("/", ctx -> {
            //set up response and give result
            ctx.status(200);
            ctx.result("Hello");
        });

        //Note Routes
        app.get("/notes", ctx -> {
            //turn notes array into json
            Gson gson = new Gson();
            String outJson = gson.toJson(notes);

            //Set up response and return result
            ctx.status(200);
            ctx.result(outJson);
        });

        app.get("/notes/{index}", ctx -> {
            //get index from path, get note from array
            int index = Integer.parseInt(ctx.pathParam("index"));
            String note = notes.get(index);

            //Convert note into indexed note, turn into json
            IndexedNote indexedNote = new IndexedNote(index, note);
            Gson gson = new Gson();
            String outJson = gson.toJson(indexedNote);

            //set up response and return
            ctx.status(200);
            ctx.result(outJson);
        });

        app.put("/notes/{index}", ctx -> {
            //get data from body and path, turn into correct data type
            int index = Integer.parseInt(ctx.pathParam("index"));
            Gson gson = new Gson();
            Properties noteData = gson.fromJson(ctx.body(), Properties.class);
            String note = noteData.getProperty("content");

            //updated the note at the index, and turn the two variables into an indexed note
            notes.set(index, note);
            IndexedNote indexedNote = new IndexedNote(index, note);
            String outJson = gson.toJson(indexedNote);

            //Set up response and return
            ctx.status(200);
            ctx.result(outJson);
        });

        app.delete("/notes/{index}", ctx -> {
            //get index from path and remove at index
            int index = Integer.parseInt(ctx.pathParam("index"));
            notes.remove(index);

            //Set up response and return
            ctx.status(204);
            ctx.result("");
        });

        app.post("/notes", ctx -> {
            //Get note from body and turn into string
            Gson gson = new Gson();
            Properties noteData = gson.fromJson(ctx.body(), Properties.class);
            String note = noteData.getProperty("content");

            //Add note to array, create indexed note dto, and turn to json
            notes.add(note);
            IndexedNote indexedNote = new IndexedNote(notes.size() - 1, note);
            String outJson = gson.toJson(indexedNote);

            //Set up response and return result
            ctx.status(201);
            ctx.result(outJson);
        });

        //Document Routes
        app.post("/documents", ctx -> {
            //Get document contents from request body
            Gson gson = new Gson();
            Properties documentData = gson.fromJson(ctx.body(), Properties.class);
            String document = documentData.getProperty("content");

            //Set up file and write document content to it
            UUID uuid = UUID.randomUUID();
            File documentFile = new File(uuid.toString() + ".txt");
            documentFile.createNewFile();
            FileWriter documentWriter = new FileWriter(documentFile.getName());
            documentWriter.write(document);
            documentWriter.close();

            //Set up response and return result
            NamedDocument namedDocument = new NamedDocument(uuid.toString(), document);
            String outJson = gson.toJson(namedDocument);
            ctx.status(201);
            ctx.result(outJson);
        });

        //Math Routes
        app.get("/math/{num1}/{num2}/{amount}", ctx -> {
            //Get all path params
            long num1 = Long.valueOf(ctx.pathParam("num1"));
            long num2 = Long.valueOf(ctx.pathParam("num2"));
            long amount = Long.valueOf(ctx.pathParam("amount"));

            //Do our operation amount times
            for (long i = 0; i < amount; i++) {
                long temp = num1 * num2;
            }

            //set up response and return
            ctx.status(200);
            ctx.result("done");

        });

        app.get("factorial/{num}", ctx -> {
            //Set up needed variables
            int num = Integer.parseInt(ctx.pathParam("num"));
            BigInteger product = BigInteger.valueOf(1);
            long startTime = System.nanoTime();

            //Perform factorial operation and output time to console
            for (int i = 2; i <= num; i++) {
                product = product.multiply(BigInteger.valueOf(i));
            }
            System.out.println("Factor time = " + (System.nanoTime() - startTime) / 1000000.0 + "ms");

            //Set up response and return
            ctx.status(200);
            ctx.result(product.toString());
        });

        app.get("/coordinates/{amount}", ctx -> {
            //get variable from path, and set up coordinate list
            long amount = Long.valueOf(ctx.pathParam("amount"));
            List<Coordinate> coordinates = new ArrayList();

            //Logic of making coordinates
            for (long i = 0; i < amount; i++) {
                double longitude = Math.random() * 180 - 90;
                double latitude = Math.random() * 360 - 180;
                String nsHemi = latitude > 0 ? "North" : "South";
                String ewHemi = longitude > 0 ? "East" : "West";
                Coordinate coordinate = new Coordinate(latitude, longitude, nsHemi, ewHemi);
                coordinates.add(coordinate);
            }

            //turn list into json, set up response and return
            Gson gson = new Gson();
            String outJson = gson.toJson(coordinates);
            ctx.status(200);
            ctx.result(outJson);
        });

        app.start();
    }

}
