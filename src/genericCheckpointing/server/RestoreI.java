package genericCheckpointing.server;

import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StrategyI;

public interface RestoreI extends StoreRestoreI{
	SerializableObject readObj(StrategyI wireFormat, String fileName);
}
