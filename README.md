# IR Search Engine

Project ini merupakan implementasi sederhana sistem Information Retrieval menggunakan Java. Program dapat membaca dataset dokumen, melakukan preprocessing, membangun inverted index, serta menjalankan pencarian dokumen.

## Fitur

- Preprocessing teks
- Inverted index
- Single-term search
- Boolean Retrieval dengan operator `AND`, `OR`, dan `NOT`
- Wildcard search, contoh: `comput*`
- Spelling correction menggunakan Levenshtein Distance, contoh: `computerr -> computer`

## Struktur Folder

```text
IR_Search_Engine/
├── data/
│   └── dokumen.txt
├── src/
│   ├── Main.java
│   ├── core/
│   ├── preprocessing/
│   ├── booleanquery/
│   ├── tolerant/
│   └── util/
├── Laporan IR.pdf
├── link_video_demo.txt
└── README.md
```

## Cara Menjalankan Program

Pastikan Java JDK sudah terpasang.

Cek Java:

```bash
java -version
javac -version
```

Program harus dijalankan dari folder utama project, yaitu folder yang berisi folder `src` dan `data`.

## Windows CMD

Masuk ke folder project:

```cmd
cd ProjectFolder
```

Buat folder output:

```cmd
mkdir out
```

Compile program:

```cmd
javac -sourcepath src -d out src\Main.java
```

Run program:

```cmd
java -cp out Main
```

Jika folder `out` sudah ada, command `mkdir out` boleh dilewati.

## Mac / Linux Terminal

Masuk ke folder project:

```bash
cd ProjectFolder
```

Buat folder output:

```bash
mkdir -p out
```

Compile program:

```bash
javac -d out $(find src -name "*.java")
```

Run program:

```bash
java -cp out Main
```

## Contoh Query

```text
computer
information AND retrieval
computer OR software
computer NOT library
comput*
computerr
retrival
documnt
```

## Link Video Demo

Link video demo terdapat pada file:

```text
link_video_demo.txt
```

## Repository

```text
https://github.com/Tobias310504/IR_Search_Engine
```