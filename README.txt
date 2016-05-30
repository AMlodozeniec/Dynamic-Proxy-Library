CS442 Design Patterns
Spring 2016
PROJECT 5 README FILE

Due Date: Sunday, May 8th, 2016
Submission Date: Wednesday, May 4th, 2016
Grace Period Used This Project: 0 Days
Grace Period Remaining: 3 Days
Author(s): Adam Mlodozeniec
e-mail(s): amlodoz1@binghamton.edu


TO COMPILE:
In the directory containing this README:
ant  all

TO RUN:
In the directory containing this README:
ant run -Darg0=mode -Darg1=num_objects -Darg2=file_name

TO CLEAN:
ant clean

TO TARBALL:
ant tarzip

TO UNTAR:
gunzip adam_mlodozeniec_assign5.tar.gz
tar -xvf adam_mlodozeniec_assign5.tar

DATA STRUCTURE USED:


JUSTIFICATION:

PURPOSE:
The purpose of this assignment was to understand how to use dynamic
proxies and Java Reflection to create a generic library to serialize
and deserialize	objects. We also used the Strategy Pattern to allow
the conversion of objects into a wire format.

PERCENT COMPLETE:
100%

PARTS THAT ARE NOT COMPLETE:
None.

BUGS:
None found.

FILES:
adam_mlodozeniec_assign5/
adam_mlodozeniec_assign5/genericCheckpointing/
adam_mlodozeniec_assign5/genericCheckpointing/src/
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/util/
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/util/SerializableObject.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/util/ProxyCreator.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/util/FileProcessor.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/util/MyAllTypesFirst.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/util/MyAllTypesSecond.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/server/
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/server/StoreRestoreI.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/server/RestoreI.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/server/StoreI.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/xmlStoreRestore/
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/xmlStoreRestore/StrategyI.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/xmlStoreRestore/XMLDeserializerStrategy.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/xmlStoreRestore/XMLSerializerStrategy.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/xmlStoreRestore/StoreRestoreHandler.java
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/xmlStoreRestore/.StoreRestoreHandler.java.swp
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/driver/
adam_mlodozeniec_assign5/genericCheckpointing/src/genericCheckpointing/driver/Driver.java
adam_mlodozeniec_assign5/genericCheckpointing/build.xml
adam_mlodozeniec_assign5/genericCheckpointing/README.txt

SAMPLE OUTPUT:

mode = serdeser
> ant run -Darg0=serdeser -Darg1=2 -Darg2=output.txt

stdout:     
[java] 0 mismatched objects

Inside output.txt: 
<DPSerialization>
 <complexType xsi:type=class genericCheckpointing.util.MyAllTypesFirst>
  <myInt xsi:type="xsd:int">2342</myInt>
  <myLong xsi:type="xsd:long">22241</myLong>
  <myString xsi:type="xsd:string">work you buffoon</myString>
  <myBool xsi:type="xsd:boolean">true</myBool>
 </complexType>
</DPSerialization>
<DPSerialization>
 <complexType xsi:type=class genericCheckpointing.util.MyAllTypesSecond>
  <myDoubleT xsi:type="xsd:double">1330.9292176023719</myDoubleT>
  <myFloatT xsi:type="xsd:float">1014.4431</myFloatT>
  <myShortT xsi:type="xsd:short">1592</myShortT>
  <myCharT xsi:type="xsd:char">o</myCharT>
 </complexType>
</DPSerialization>
<DPSerialization>
 <complexType xsi:type=class genericCheckpointing.util.MyAllTypesFirst>
  <myInt xsi:type="xsd:int">2766</myInt>
  <myLong xsi:type="xsd:long">100172</myLong>
  <myString xsi:type="xsd:string">work you buffoon</myString>
  <myBool xsi:type="xsd:boolean">false</myBool>
 </complexType>
</DPSerialization>
<DPSerialization>
 <complexType xsi:type=class genericCheckpointing.util.MyAllTypesSecond>
  <myDoubleT xsi:type="xsd:double">4513.979591677021</myDoubleT>
  <myFloatT xsi:type="xsd:float">1369.4502</myFloatT>
  <myShortT xsi:type="xsd:short">1041</myShortT>
  <myCharT xsi:type="xsd:char">b</myCharT>
 </complexType>
</DPSerialization>

mode = deser
> ant run -Darg0=deser -Darg1=2 -Darg2=output.txt

stdout: 
	 [java] MyAllTypesFirst -> Int: 2342, Long: 22241, Bool: true, String: work you buffoon
     [java] MyAllTypesSecond -> Double: 1330.9292176023719, Float: 1014.4431, Short: 1592, Char: o
     [java] MyAllTypesFirst -> Int: 2766, Long: 100172, Bool: false, String: work you buffoon
     [java] MyAllTypesSecond -> Double: 4513.979591677021, Float: 1369.4502, Short: 1041, Char: b

BIBLIOGRAPHY:
This serves as evidence that I am in no way intending Academic 
Dishonesty.

Adam Mlodozeniec
