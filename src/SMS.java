public class SMS {
    private int senderIndex;
    private int receiverIndex;
    private String message;

    public SMS() {
    }

    public SMS(int senderIndex, int receiverIndex, String message) {
        this.senderIndex = senderIndex;
        this.receiverIndex = receiverIndex;
        this.message = message;
    }

    public int getSenderIndex() {
        return senderIndex;
    }

    public void setSenderIndex(int senderIndex) {
        this.senderIndex = senderIndex;
    }

    public int getReceiverIndex() {
        return receiverIndex;
    }

    public void setReceiverIndex(int receiverIndex) {
        this.receiverIndex = receiverIndex;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "From SIM card " + senderIndex + "to SIM card " + receiverIndex + ": " + message;
    }
}
