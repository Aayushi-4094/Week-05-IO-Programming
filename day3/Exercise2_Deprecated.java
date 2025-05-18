// File: Exercise2_Deprecated.java
class LegacyAPI {
    @Deprecated
    public void oldFeature() {
        System.out.println("This is an old feature");
    }

    public void newFeature() {
        System.out.println("This is a new feature");
    }
}

public class Exercise2_Deprecated {
    public static void main(String[] args) {
        LegacyAPI api = new LegacyAPI();
        api.oldFeature(); // shows warning
        api.newFeature();
    }
}
