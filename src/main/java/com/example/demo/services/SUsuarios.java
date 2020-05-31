package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.example.demo.models.User;


@Service
public class SUsuarios {
	//servicios. codigo de usuario.
	
	//creamos un objeto regions para avisar en que region buscamos la base de datos.
	Regions region = Regions.SA_EAST_1; // no hago new por que es estatica-
	AmazonDynamoDB client = AmazonDynamoDBClientBuilder
			.standard()//El client es el objeto que maneja la coneccion con AWS. Como escliente Dynamo, Base de datos
			.withRegion(region)//este region es el que cree arriba.
			.build();
	String tableName = "User";//tabla con nombre exacto de la tabla creada en AWS.
	
	public List<User> getAll()//es una lista de User (siendo user la clase del Model. la importo.
	{
		ScanRequest scanner = new ScanRequest().withTableName(tableName);//Creo un scanner que CUANDO se corra, se corre en esta tabla
		ScanResult result= client.scan(scanner);//scanResult: obtengo el resultado cuando client.scan CORRE el scanner. y me lo pasa a result
		
		//Ahora recorro y lo tiro a una lista.
		List<User> listUser = new ArrayList<User>();
		
		for(Map<String, AttributeValue> x: result.getItems() )//result.getItems devuelve una lista de mapas
		{
			User user = new User();
			user.setId(Integer.parseInt(x.get("id").getN()));//Al ID de la instancia de Usuario seteale
			//el AtributeValue de X con la Key "id", que es un numero. PERO LO TRAE como string asi que parsealo.
			user.setName(x.get("name").getS());
			user.setDni(Integer.parseInt(x.get("dni").getN()) );
			user.setLastName(x.get("lastName").getS());
			user.setSex(Integer.parseInt(x.get("sex").getN()) );
			
			listUser.add(user);
		}
		
		return listUser;
	}
	
	
	public User getUser(int id)
	{
		User user = new User();
		DynamoDB claw = new DynamoDB(client);//
		Table table = claw.getTable(tableName);
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("id", id);
		try {
			Item usuario = table.getItem(spec);
			user.setDni(usuario.getInt("dni"));
			user.setId(id);
			user.setName(usuario.getString("name"));
			user.setLastName(usuario.getString("lastName"));
			user.setSex(usuario.getInt("sex"));
			
			return user;
		}
		catch(Exception e){
			return new User();
		}
		
	}
	
	
}
