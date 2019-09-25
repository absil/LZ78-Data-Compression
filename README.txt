COMPX301-19A — Assignment Two
LZ78 Compression
Due: Wednesday, April 24th, 2019 — 11:00pm
Description: Implement LZ78 compression and decompression. This assignment is to be done in pairs (i.e. with a partner), and implemented in Java such that your code compiles and runs on the Linux machines in the R Block labs.

Specification: Implement the LZ78 compression and decompression routines according to the following specifications:

A B+ solution will have two parts: an encoder, and a decoder. A complete A+ will have two additional parts, a bit-packer, and a bit-unpacker; all separate programs that read standard input and produce standard output.
Your encoder takes its input as a stream of bytes from standard input, and uses the LZ78 algorithm to output pairs, where each pair consists of a phrase number and a mismatched byte, one pair per line of output.
Given the output of your encoder as input, the output of your decoder should exactly match the input to the encoder (i.e. this is the complement operation to your encoder).
Given the output of your encoder as input, the output of the bit-packer is a stream of bytes encoding the input in as few bits as are needed (i.e. log p bits per phrase number when there are p patterns in the dictionary).
Given the output of your bit-packer as input, the output of your bit-unpacker should exactly match the input to the bit-packer (i.e. this is the complement operation to your bit-packer).
The encoder must utilise a multiway trie (not hashing) for the dictionary.
To assist in marking, the Java program classes must be called LZencode, LZdecode, LZpack and LZunpack, respectively.
Approach: I suggest you get your encoder to work first then build your decoder and test them as a pair. Once this works, turn your attention to building the packer and unpacker. A passing mark is possible if just the encoder and decoder work, but a mark above B+ is only possible if all four programs are completed and comply with all specifications.

Hand in: Submission is done electronically via moodle in the same way as the previous assignment. That is, place into an otherwise empty directory only your source code and an optional plain-text README file. Name the directory ID1_ID2, where ID1 and ID2 are the student ID numbers of the partnership. Create a single, compressed archive (see the unix manual on "zip" or "tar") and have one member of the partnership upload your solution via Moodle. The other partner should check that the upload was successful. Marks are awarded based upon conformance to the specification and quality of the code, including format and documentation (i.e. it should look nice and be understandable). Names and student ID numbers for both partners should be in the header documentation of all files.

Tony C. Smith, 28/03/2019

LZencodes runs using "java LZencode < "file to encode" > "file to output pairs"
LZpack runs using "java LZpack < "file to pack" > "file to output packed file"
LZunpack runs using "java LZunpack < "file to unpack" > "file to output unpacked file"
LZdecode runs using "java LZdecode < "file to decode" > "file to output decoded file"
