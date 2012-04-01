
import com.sun.javadoc.*;
import java.util.*;
import java.text.*;
import java.io.*;


public class testDoclet{

    static private PrintWriter out = null;
    static private int ref = 1;
    static private String outputFileName="group4.tex";
    static private RootDoc doc=null;
   
 public static boolean start(RootDoc root) {
    ClassDoc[] classes = root.classes();

// Open the output file
        try {
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(new File(outputFileName))));
        }
        catch (IOException exc) {
            doc.printError("Error opening output file");
            return false;
        }


   headerLatex();

	out.println("\\huge{\\textbf{Classes Summary}}\\\\");  
	for (int i = 0; i < classes.length; ++i) {
            ClassDoc cd = classes[i];
	    out.println("\\LARGE{\\textbf{"+cd+"}}\\\\");
	} 
	out.println("\\newpage");
	
	for (int i = 0; i < classes.length; ++i) {
            ClassDoc cd = classes[i];
	    out.println("\\LARGE{\\textbf{"+cd+"}}\\\\");
	    out.println(classes[i].getRawCommentText()+"\\\\");
	    FieldDoc[] fields = classes[i].fields();
	    printFields(fields);
  	    printConstructor(cd.constructors());
            printMethod(cd.methods());
	    
    	    
         }

   endLatex();

   
   return true;
  }




   public static void headerLatex() {
	out.println("\\documentclass[10pt]{article}");
	out.println("\\usepackage[latin1]{inputenc}");
	out.println("\\usepackage[T1]{fontenc}");
	out.println("\\usepackage[francais]{babel}");
	out.println("\\usepackage{soul}");
	out.println("\\usepackage[top=3cm, bottom=3cm, left=3cm, right=3cm]{geometry}");
        out.println("\\begin{document}");
	out.println("\\noindent");
    } 	

   public static void endLatex(){
  	out.println("\\end{document}");
	out.close();
   }

  public static void printFields(FieldDoc[] fields){
	out.println("\\noindent");
	out.println("\\huge{\\textbf{Fields Summary}}\\\\");
	for (int j=0; j<fields.length; j++) {

            	out.println("\\\\"+"\\textbf{"+fields[j].name()+"}\\\\");
		out.println(fields[j].type()+"\\\\");
		out.println(fields[j].commentText()+"\\\\");
   	    }
  }

  public static void printConstructor(ConstructorDoc[] mems) {
        for (int i = 0; i < mems.length; ++i) {
	String[] listeComments;
	listeComments = new String[mems.length];
	listeComments[i] = mems[i].commentText();
	
	
	
            ParamTag[] params = mems[i].paramTags();
            out.println("\\textbf{"+mems[i].name()+"}\\\\");
	    if (mems[i].flatSignature().length() >2) {
	    out.println("Sa signature est \\textbf{"+mems[i].flatSignature()+"}\\\\");
		}
	   else {
	}
	    out.println(listeComments[i]+"\\\\"); 
            for (int j = 0; j < params.length; ++j) {
                out.println("parametre: "+params[j].parameterName()
                    + " - " + params[j].parameterComment()+"\\\\");
		
		}
		

        }
    }   
 public static void printMethod(MethodDoc[] mems) {
        for (int i = 0; i < mems.length; ++i) {
	String[] listeComments;
	listeComments = new String[mems.length];
	listeComments[i] = mems[i].commentText();
	
	
	
            ParamTag[] params = mems[i].paramTags();
            out.println("\\textbf{"+mems[i].returnType() +" "+ mems[i].name()+"}\\\\");
	    /* La ligne suivante nous a permis de détermienr les conditions de test pour l'écriture du if */
 //out.println(mems[i].flatSignature().length()+"\\\\");
	   if (mems[i].flatSignature().length() >2) {
	    out.println("Sa signature est \\textbf{"+mems[i].flatSignature()+"}\\\\");
		}
	    else {
	}
	    out.println(listeComments[i]+"\\\\"); 
            for (int j = 0; j < params.length; ++j) {
                out.println("parametre: "+params[j].parameterName()
                    + " - " + params[j].parameterComment()+"\\\\");
		
		}
		

        }
    }  
}


