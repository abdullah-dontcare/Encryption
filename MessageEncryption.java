class MessageEncryption extends Encryption {
    final String password;

    public MessageEncryption(String password) {
        this.password = password;
    }

    @Override
    public String encryptMessage(String message) {
        char[] messageArray = message.toCharArray();

        // Encrypt the message by shifting each character's ASCII value by 4
        for (int i = 0; i < messageArray.length; i++) {
            messageArray[i] = (char) (messageArray[i] + 4);
        }

        return new String(messageArray);
    }

    @Override
    public String decryptMessage(String encryptedMessage) {
        char[] messageArray = encryptedMessage.toCharArray();

        // Decrypt the message by shifting each character's ASCII value back by 4
        for (int i = 0; i < messageArray.length; i++) {
            messageArray[i] = (char) (messageArray[i] - 4);
        }

        return new String(messageArray);
    }
}