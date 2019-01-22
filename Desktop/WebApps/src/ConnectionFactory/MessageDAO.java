package ConnectionFactory;

import model.Message;
import model.User;

public interface MessageDAO {
	public void createMessage(Message message);
	public Message searchMessageById(int id); 
	public void editMessage(Message message);
	public void deleteMessage(int id);
}
