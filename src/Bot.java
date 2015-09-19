public class Bot
	{
	protected Genome genome;
	protected int[] memory = new int[100];
	//bot data
	protected int age = 0;
	protected int energy = 0;
	Bot()
		{
		genome = new Genome();
		flashMemory();
		}
	private void flashMemory()
		{
		for (int i=0;i<memory.length;i++)
			{
			memory[i] = 0;
			}
		}
	private void flashFunctionalMemory()
		{
		for (FunctionalMemory fm: FunctionalMemory.values())
			{
			memory[fm.getMemoryLocation()] = 0;
			}
		}
	private void executeFunctionalMemory()
		{
		for (FunctionalMemory fm: FunctionalMemory.values())
			{
			fm.execute(this,fm.getMemoryLocation());
			}
		}
	public void update()
		{
		flashFunctionalMemory();
		genome.execute(this);
		executeFunctionalMemory();
		}
	}
