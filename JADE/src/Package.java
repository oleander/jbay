class Package {
    private Message message;
    private String status;

    public Package(String status, Message message) {
        this.status = status;
        this.message = message;
    }

    public Message getMessage(){
        return this.message;
    }

    public String getStatus(){
        return this.status;
    }
}