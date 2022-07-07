package item6;

public class Deprecation {

    /**
     * @deprecated in favor of
     * {@link #Deprecation(String}
     */
    // 애노테이션 프로세서
    @Deprecated(forRemoval = true, since = "1.2")
    public Deprecation() {
    }

    private String name;

    public Deprecation(String name) {
        this.name = name;
    }
}
