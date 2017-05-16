public class BaseLed {
    private boolean emitting = false;

    public void startEmitting() {
        this.emitting = true;
    }

    public void stopEmitting() {
        this.emitting = false;
    }

}
