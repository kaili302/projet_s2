import com.sun.javadoc.*;

public class ListParams extends Doclet {

    public static boolean start(RootDoc root) {
        ClassDoc[] classes = root.classes();
        for (int i = 0; i < classes.length; ++i) {
            ClassDoc cd = classes[i];
            printMembers(cd.constructors());
            printMembers(cd.methods());
        }
        return true;
    }

    static void printMembers(ExecutableMemberDoc[] mems) {
        for (int i = 0; i < mems.length; ++i) {
            ParamTag[] params = mems[i].paramTags();
            System.out.println(mems[i].qualifiedName());
            for (int j = 0; j < params.length; ++j) {
                System.out.println("   " + params[j].parameterName()
                    + " - " + params[j].parameterComment());
            }
        }
    }        
}
