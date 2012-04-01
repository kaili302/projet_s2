import com.sun.javadoc.*;
import java.text.*;
public class SimpleDoclet extends Doclet {
public static boolean start(RootDoc root){
  //iterate over all classes.
  int i,j,k;
  ClassDoc[] classes = root.classes();
  for (i=0; i< classes.length; i++) {
    //iterate over all methods and print their names.
    MethodDoc[] methods = classes[i].methods();
    out("Methods");
    out("-------");
    for (j=0; j<methods.length; j++) {
      out("Method: name = " + methods[j].name());
    }
    out("Fields");
    out("------");
    //iterate over all fields, printing name, comment text, and type.
    FieldDoc[] fields = classes[i].fields();
    for (j=0; j<fields.length; j++) {
      Object[] field_info = {fields[j].name(), fields[j].commentText(),
                    fields[j].type()};
      out(FIELDINFO.format(field_info));
      //iterate over all field tags and print their values.
      Tag[] tags = fields[j].tags();
      for (k=0; k<tags.length; k++) {
     out("\tField Tag Name= " + tags[k].name());
     out("\tField Tag Value = " + tags[k].text());
      }
    }
  }
  //No error processing done, simply return true.
  return true;
}
private static void out(String msg) {
  System.out.println(msg);
}
private static MessageFormat METHODINFO =
  new MessageFormat("Method: return type {0}, name = {1};");
private static MessageFormat FIELDINFO =
  new MessageFormat("Field: name = {0}, comment = {1}, type = {2};");
}
