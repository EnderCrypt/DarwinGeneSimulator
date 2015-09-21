public class Bot
	{
	protected Genome genome;
	protected Memory memory;
	//bot data
	protected int age = 0;
	protected int energy = 1000;
	Bot()
		{
		memory = new Memory(100,10000);
		genome = new Genome(memory);
		}
	public void update()
		{
		memory.flashFunctionalMemory();
		genome.execute(this);
		memory.executeFunctionalMemory(this);
		memory.printMemory();
		}
	}
