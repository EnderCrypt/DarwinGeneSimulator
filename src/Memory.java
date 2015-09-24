public class Memory
	{
	private int[] memory;
	private final int numLimit;
	Memory(int size, int numLimit)
		{
		memory = new int[size];
		this.numLimit = numLimit;
		flashMemory();
		}
	public int getNumLimit()
		{
		return numLimit;
		}
	public boolean put(int location, int number)
		{
		if ((location >= 0) && (location < memory.length))
			{
			number = Math.max(number, -numLimit);
			number = Math.min(number, numLimit);
			memory[location] = number;
			return true;
			}
		else
			{
			return false;
			}
		}
	public int get(int location)
		{
		if ((location >= 0) && (location < memory.length))
			{
			return memory[location];
			}
		else
			{
			return 0;
			}
		}
	public void flashMemory()
		{
		for (int i=0;i<memory.length;i++)
			{
			memory[i] = 0;
			}
		}
	public void flashFunctionalMemory()
		{
		for (FunctionalMemory fm: FunctionalMemory.values())
			{
			memory[fm.getMemoryLocation()] = 0;
			}
		}
	public void executeFunctionalMemory(Bot bot)
		{
		for (FunctionalMemory fm: FunctionalMemory.values())
			{
			fm.execute(bot,fm.getMemoryLocation());
			}
		}
	public void printMemory()
		{
		System.out.println("Debug print of "+this);
		int num = 0;
		while (num < memory.length)
			{
			String out = num+":\t";
			for (int i=0;(num+i<memory.length) && (i<10);i++)
				{
				out = out+get(num+i);
				if (i< 9)
					{
					out = out + '\t';
					}
				}
			System.out.println(out);
			num += 10;
			}
		}
	}
