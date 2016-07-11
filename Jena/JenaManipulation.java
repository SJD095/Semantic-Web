package org.zysun.sw_examples.jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.FileManager;
import java.io.InputStream;

public class JenaManipulation {
	public static void main(String[] args) {
	
		String inputFileName = "src/resource/13331233_sunzhongyang.xml";
        InputStream input = FileManager.get().open( inputFileName );
        if (input == null) {
            throw new IllegalArgumentException(
                    "File: " + inputFileName + " not found");
        }
        
        Model basic = ModelFactory.createDefaultModel();
        
        basic.read(input, null);
        
        String title = "http://xmlns.com/fofa/0.1/title";
        String fofa = "http://example.org/LXC";
        String using = "Mrs";
        
        Property P = basic.createProperty(title);
        Resource LXC = basic.createResource(fofa);
        basic.add( LXC, P, title);
        
        basic.write(System.out, "TURTLE");
        basic.write(System.out, "RDF/XML");
        }
}
