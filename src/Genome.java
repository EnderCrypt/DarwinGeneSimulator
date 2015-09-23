import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Genome
	{
	public List<Gene> genes = new ArrayList<>();
	Genome(Memory memory)
		{
		int genesToAdd = 250 + (int) (Math.random()*250);
		for (int i=0;i<genesToAdd;i++)
			{
			genes.add(new Gene(memory));
			}
		}
	public void execute(Bot bot)
		{
		Stack<Integer> intStack = new Stack<>();
		Stack<Boolean> boolStack = new Stack<>();
		
		Iterator<Gene> iterator = genes.iterator();
		
		ExecData execData = new ExecData(bot, iterator, intStack, boolStack);
		
		while(iterator.hasNext())
			{
			execData.gene = iterator.next();
			execData.gene.execute(execData);
			}
		}
	class ExecData
		{
		Bot bot;
		Stack<Integer> intStack;
		Stack<Boolean> boolStack;
		Iterator<Gene> iterator;
		Gene gene;
		public ExecData(Bot bot, Iterator<Gene> iterator, Stack<Integer> intStack, Stack<Boolean> boolStack)
			{
			this.bot = bot;
			this.iterator = iterator;
			this.intStack = intStack;
			this.boolStack = boolStack;
			}
		}
	}
