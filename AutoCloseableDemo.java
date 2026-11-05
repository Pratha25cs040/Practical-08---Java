class SampleResource implements AutoCloseable {
    public SampleResource() {
        System.out.println("Resource opened.");
    }

    @Override
    public void close() {
        System.out.println("Resource closed.");
    }
}

public class AutoCloseableDemo {
    public static void main(String[] args) {
        try (SampleResource resource = new SampleResource()) {
            throw new Exception("Original error occurred.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
