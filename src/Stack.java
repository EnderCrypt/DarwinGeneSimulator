import java.util.ArrayList;
import java.util.List;

public class Stack <T>
	{
	private List<T> stack = new ArrayList<>();
	public void clear()
		{
		stack.clear();
		}
	public void push(T data)
		{
		stack.add(data);
		}
	public T pull()
		{
		return stack.remove(stack.size());
		}
	}
