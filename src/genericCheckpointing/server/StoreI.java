package genericCheckpointing.server;

import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.xmlStoreRestore.StrategyI;

public interface StoreI extends StoreRestoreI{
	void writeObj(MyAllTypesFirst aRecord, StrategyI wireFormat, String fileName);
	void writeObj(MyAllTypesSecond aRecord, StrategyI wireFormat, String fileName);
}
