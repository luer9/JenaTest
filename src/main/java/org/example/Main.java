package org.example;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;

public class Main {
    public static final String TDB_DIR = "F:\\data\\kleene\\datasets\\dbpediaOri";
    public static void main(String[] args) {
        Dataset dataset = TDBFactory.createDataset(TDB_DIR);
//        // 创建一个空的RDF模型
//        Model model = ModelFactory.createDefaultModel();
//
//        // 使用FileManager加载RDF数据文件到模型中
//        FileManager.get().readModel(model, "path/to/your/rdf_file.rdf");
        // 执行SPARQL查询
        String sparqlQuery = "SELECT ?subject  ?object WHERE {?subject <http://dbpedia.org/ontology/locatedInArea>* ?object}";
        Query query = QueryFactory.create(sparqlQuery);
        try (QueryExecution qexec = QueryExecutionFactory.create(query, dataset)) {
            ResultSet results = qexec.execSelect();
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                RDFNode subject = soln.get("subject");
//                RDFNode predicate = soln.get("predicate");
                RDFNode object = soln.get("object");
                System.out.println(subject + " " + object);
//                System.out.println(subject + " " + predicate + " " + object);
            }
        }
    }
}