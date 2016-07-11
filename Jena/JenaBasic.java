package org.zysun.sw_examples.jena;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class JenaBasic {
	public static void main(String[] args) {
	
		String uriStudent = "http://example.org/resource/$sunzhongyang$";
        String uriSaid = "http://example.org/property/said";
        String helloWorld = "Hello World!";
        
        Model basic = ModelFactory.createDefaultModel();
        Property P = basic.createProperty(uriSaid);
        Resource myself = basic.createResource(uriStudent);
        basic.add( myself, P, helloWorld);
        
        StmtIterator iter = basic.listStatements();
        
        while (iter.hasNext()) {
        	Statement stmt      = iter.nextStatement();
            Resource  subject   = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object    = stmt.getObject();

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
	}
}
