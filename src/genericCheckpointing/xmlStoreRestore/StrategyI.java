package genericCheckpointing.xmlStoreRestore;

import genericCheckpointing.util.FileProcessor;

public interface StrategyI{
	public Object check(Object obj, FileProcessor fp);
}
