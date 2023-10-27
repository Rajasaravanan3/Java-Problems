import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface AlternativeName {
    public String value();
}

public class CopyObjToObj {

    public <T> T convertObject(Object source, Class<T> destination) {

        Field[] destFields = destination.getDeclaredFields();
        Field[] sourceFields = source.getClass().getDeclaredFields();

        T returnSrc = null;
        Object destinationObj = null;
        try {
            returnSrc = destination.newInstance();
            Method getInstanceMethod = destination.getMethod("getInstance");
            destinationObj = getInstanceMethod.invoke(returnSrc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Field srcField : sourceFields) {
            for (Field destField : destFields) {

                AlternativeName alternative = destField.getAnnotation(AlternativeName.class);

                if (srcField.getType().equals(destField.getType())) {
                    boolean altName = (alternative != null);
                    if ((srcField.getName().equals(destField.getName()))
                            || (altName && srcField.getName().equals(alternative.value()))) {
                        try {
                            String targetFieldName = destField.getName();
                            String sourceFieldName = srcField.getName();
                            String getterName = "get" + Character.toUpperCase(sourceFieldName.charAt(0))
                                    + sourceFieldName.substring(1);
                            String setterName = "set" + Character.toUpperCase(targetFieldName.charAt(0))
                                    + targetFieldName.substring(1);

                            Method sourceGetter = source.getClass().getMethod(getterName);
                            Method targetSetter = destination.getMethod(setterName, srcField.getType());

                            Object value = sourceGetter.invoke(source);
                            targetSetter.invoke(destinationObj, value);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        System.out.println("Destination Field Values:");
        for (Field field : destFields) {
            try {
                field.setAccessible(true);
                System.out.print(field.get(destinationObj) + " ");
            } catch (Exception e) {
                System.out.println("Exception");
            }
        }
        System.out.println();

        return returnSrc;
    }

    public static void main(String[] args) {

        CopyObjToObj obj = new CopyObjToObj();
        try {
            obj.convertObject(SourceClass.getInstance(), DestinationClass.class);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }
}

class SourceClass {

    private int CID = 10;
    String cName = "SRT";
    int permanentId = 100;
    protected int salary = 100100;

    SourceClass() {
    }

    public static SourceClass getInstance() {
        return new SourceClass();
    }

    public int getCID() {
        return CID;
    }

    public String getCName() {
        return cName;
    }

    public int getPermanentId() {
        return permanentId;
    }

    public int getSalary() {
        return salary;
    }

    public void setCID(int id) {
        this.CID = id;
    }

    public void setcName(String name) {
        this.cName = name;
    }

    public void setPermanentId(int permanentId) {
        this.permanentId = permanentId;
    }
}

class DestinationClass {

    @AlternativeName("CID")
    int UID;

    @AlternativeName("cName")
    String uName;

    int permanentId;

    DestinationClass() {
    }

    public static DestinationClass getInstance() {
        return new DestinationClass();
    }

    public int getUID() {
        return UID;
    }

    public String getUName() {
        return uName;
    }

    public int getPermanentId() {
        return permanentId;
    }

    public void setUID(int id) {
        this.UID = id;
    }

    public void setUName(String name) {
        this.uName = name;
    }

    public void setPermanentId(int permanentId) {
        this.permanentId = permanentId;
    }
}