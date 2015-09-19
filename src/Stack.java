import java.util.ArrayList;
import java.util.List;

public class Stack <T>
	{
	private T defaultData;
	private List<T> stack = new ArrayList<>();
	Stack(T defaultData)
		{
		this.defaultData = defaultData;
		}
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
		if (stack.size() > 0)
			{
			return stack.remove(stack.size());
			}
		else
			{
			return defaultData;
			}
		}
	}
