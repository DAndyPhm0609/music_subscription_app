package com.example.demo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.util.IOUtils;
import com.example.demo.model.Music;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

import static org.apache.commons.codec.CharEncoding.UTF_8;

@SpringBootApplication
@ComponentScan({"com.example.demo.repo.MusicRepository"})
@ComponentScan({"com.example.demo.config.DynamoDbConfig"})
public class Assignment1Application implements CommandLineRunner {

	static Logger logger = LoggerFactory.getLogger(Assignment1Application.class);

	@Autowired
	DynamoDBMapper dynamoDBMapper;

	@Value("classpath:data/a1.json")
	Resource resourceFile;

	@Value("${aws.dynamodb.accessKey}")
	private String dynamodbAccessKey;

	@Value("${aws.dynamodb.secretKey}")
	private String dynamodbSecretKey;

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// TODO: see role IAM
		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Music.class);
		tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));

		read_save_music();

	}

	private void read_save_music() throws IOException, ParseException {
		if (resourceFile.exists()){
			InputStream is = Files.newInputStream(resourceFile.getFile().toPath().toFile().toPath());
			String jsonTxt = IOUtils.toString(is);
			JSONObject json = (JSONObject) JSONSerializer.toJSON( jsonTxt );
			JSONArray array = json.getJSONArray("songs");
			for (int i = 0 ; i < array.size() - 1; i++){
				System.out.println(array.get(i));

				JSONObject music_json_object = (JSONObject) array.get(i);
				String title = music_json_object.getString("title");
				String artist = music_json_object.getString("artist");
				int year = music_json_object.getInt("year");
				String web_url = music_json_object.getString("web_url");
				String img_url = music_json_object.getString("img_url");

				Music music = new Music(title, artist, year, web_url, img_url);
				dynamoDBMapper.save(music);
				break;
			}

		}
	}
}
