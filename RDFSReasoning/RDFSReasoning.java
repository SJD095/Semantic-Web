package org.zysun.sw_examples.jena;

import java.io.InputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class RDFSReasoning {
	 public static void main(String[] args) {
		 
		 String inputFileName = "src/resource/tourism.xml";
	        InputStream input = FileManager.get().open( inputFileName );
	        if (input == null) {
	            throw new IllegalArgumentException(
	                    "File: " + inputFileName + " not found");
	        }
	        
	        Model basic = ModelFactory.createDefaultModel();
	        
	        basic.read(input, null);
	        
	        InfModel inf = ModelFactory.createRDFSModel(basic); 
	        String sparql = String.format(
	                "SELECT * WHERE { ?X <%s> ?Y .}", RDF.type);
	        Query query = QueryFactory.create(sparql);
	        
	        System.out.println("Solutions under RDFS reasoning:");

	        try (QueryExecution qe = QueryExecutionFactory.create(query, inf)) {
	            ResultSet res = qe.execSelect();
	            res.forEachRemaining(System.out::println);
	        }
	        
	        basic.write(System.out, "TURTLE");
	        //basic.write(System.out, "RDF/XML");
	    }
}
