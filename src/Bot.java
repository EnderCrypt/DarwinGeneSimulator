public class Bot
	{
	protected Genome genome;
	protected Memory memory;
	//bot data
	protected int age = 0;
	protected int energy = 0;
	Bot()
		{
		genome = new Genome();
		memory = new Memory(100,0,100);
		}
	public void update()
		{
		memory.flashFunctionalMemory();
		genome.execute(this);
		memory.executeFunctionalMemory(this);
		memory.printMemory();
		}
	}
