BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "event" (
	"event_id"	INTEGER NOT NULL UNIQUE,
	"namaEvent"	VARCHAR NOT NULL,
	"deskripsi"	VARCHAR NOT NULL,
	"tanggal"	TEXT NOT NULL,
	"kuota"	INTEGER NOT NULL,
	PRIMARY KEY("event_id" AUTOINCREMENT)
);
CREATE TABLE IF NOT EXISTS "peserta" (
	"id_peserta"	INTEGER NOT NULL UNIQUE,
	"nama"	TEXT NOT NULL,
	"nim"	INTEGER NOT NULL,
	"email"	TEXT NOT NULL,
	"wa"	NUMERIC NOT NULL,
	"event_id"	INTEGER NOT NULL,
	PRIMARY KEY("id_peserta" AUTOINCREMENT),
	FOREIGN KEY("event_id") REFERENCES "event"("event_id")
);
CREATE TABLE IF NOT EXISTS "users" (
	"nim"	INTEGER NOT NULL UNIQUE,
	"email"	VARCHAR(50) NOT NULL,
	"username"	VARCHAR(50) NOT NULL UNIQUE,
	"password"	VARCHAR(100) NOT NULL,
	"role"	INTEGER NOT NULL DEFAULT 2,
	PRIMARY KEY("nim")
);
INSERT INTO "event" VALUES (17,'Seminar Teknologi Informasi','Seminar tentang perkembangan terbaru dalam dunia teknologi informasi.','2024-07-01',100);
INSERT INTO "event" VALUES (18,'Lomba Programming','Kompetisi pemrograman untuk mahasiswa dengan berbagai tingkat kesulitan.','2024-08-15',50);
INSERT INTO "event" VALUES (19,'Workshop Machine Learning','Pelatihan praktis mengenai konsep dasar dan aplikasi machine learning.','2024-09-20',30);
INSERT INTO "event" VALUES (20,'Hackathon 2024','Kompetisi membuat solusi inovatif dalam waktu terbatas.','2024-10-10',80);
INSERT INTO "event" VALUES (21,'Konferensi Startup','Acara untuk membahas strategi dan tantangan dalam membangun startup.','2024-11-05',150);
INSERT INTO "event" VALUES (22,'Seminar Kewirausahaan Digital','Seminar mengenai strategi kewirausahaan digital di era digitalisasi.','2024-07-15',120);
INSERT INTO "event" VALUES (23,'Workshop UI/UX Design','Pelatihan mendalam tentang desain antarmuka pengguna.','2024-08-25',40);
INSERT INTO "event" VALUES (24,'Lomba Data Science','Kompetisi analisis data untuk memecahkan masalah dunia nyata.','2024-09-30',60);
INSERT INTO "event" VALUES (25,'Hackathon AI','Kompetisi untuk mengembangkan solusi dengan kecerdasan buatan.','2024-10-20',90);
INSERT INTO "event" VALUES (26,'Seminar Blockchain','Seminar tentang aplikasi dan potensi teknologi blockchain.','2024-11-10',80);
INSERT INTO "event" VALUES (27,'Kursus Pemrograman Python','Kursus intensif belajar pemrograman dengan bahasa Python.','2024-07-05',25);
INSERT INTO "event" VALUES (28,'Pelatihan Big Data Analytics','Pelatihan untuk menguasai analisis data skala besar.','2024-08-18',35);
INSERT INTO "event" VALUES (29,'Lomba Web Development','Kompetisi mengembangkan aplikasi web dengan teknologi terbaru.','2024-09-22',50);
INSERT INTO "event" VALUES (30,'Seminar Teknologi Cloud','Seminar mengenai layanan dan keamanan teknologi cloud.','2024-10-15',70);
INSERT INTO "event" VALUES (31,'Workshop Internet of Things (IoT)','Pelatihan praktis tentang implementasi IoT dalam berbagai aplikasi.','2024-11-20',45);
INSERT INTO "event" VALUES (32,'Hackathon Blockchain','Kompetisi untuk mengembangkan aplikasi menggunakan teknologi blockchain.','2024-07-30',100);
INSERT INTO "event" VALUES (33,'Konferensi AI','Acara internasional membahas perkembangan terbaru dalam kecerdasan buatan.','2024-08-28',130);
INSERT INTO "event" VALUES (34,'Seminar E-commerce','Seminar tentang strategi pemasaran dan pengembangan e-commerce.','2024-09-10',75);
INSERT INTO "event" VALUES (35,'Lomba Mobile App Development','Kompetisi mengembangkan aplikasi mobile untuk berbagai platform.','2024-10-25',55);
INSERT INTO "event" VALUES (36,'Hackathon Machine Learning','Kompetisi untuk mengembangkan model machine learning inovatif.','2024-11-15',85);
INSERT INTO "event" VALUES (37,'Workshop UI/UX Design','Pelatihan mendalam tentang desain antarmuka pengguna.','2023-12-30',40);
INSERT INTO "event" VALUES (38,'Lomba Data Science','Kompetisi analisis data untuk memecahkan masalah dunia nyata.','2023-11-20',60);
INSERT INTO "event" VALUES (39,'Hackathon AI','Kompetisi untuk mengembangkan solusi dengan kecerdasan buatan.','2023-10-15',90);
INSERT INTO "event" VALUES (40,'Seminar Blockchain','Seminar tentang aplikasi dan potensi teknologi blockchain.','2023-09-10',80);
INSERT INTO "event" VALUES (41,'Kursus Pemrograman Python','Kursus intensif belajar pemrograman dengan bahasa Python.','2023-08-05',25);
INSERT INTO "peserta" VALUES (17,'John Doe',123456789,'john.doe@stis.ac.id',81234567890,22);
INSERT INTO "peserta" VALUES (18,'Jane Smith',987654321,'jane.smith@stis.ac.id',85678901234,23);
INSERT INTO "peserta" VALUES (19,'Michael Johnson',456789123,'michael.johnson@stis.ac.id',87654321098,23);
INSERT INTO "peserta" VALUES (20,'Alice Brown',567891234,'alice.brown@stis.ac.id',81111222333,24);
INSERT INTO "peserta" VALUES (21,'Bob Wilson',234567891,'bob.wilson@stis.ac.id',82222333444,22);
INSERT INTO "peserta" VALUES (22,'Eva Davis',789123456,'eva.davis@stis.ac.id',83333444555,42);
INSERT INTO "peserta" VALUES (23,'David Lee',321654987,'david.lee@stis.ac.id',84444555666,30);
INSERT INTO "peserta" VALUES (24,'Sarah White',654987321,'sarah.white@stis.ac.id',85555666777,32);
INSERT INTO "peserta" VALUES (25,'Peter Clark',987321654,'peter.clark@stis.ac.id',86666777888,33);
INSERT INTO "peserta" VALUES (26,'Adhi',222212918,'adhi@stis.ac.id',81234567890,17);
INSERT INTO "peserta" VALUES (27,'Adhi',222212918,'adhi@stis.ac.id',81234567890,18);
INSERT INTO "peserta" VALUES (28,'Adhi',222212918,'adhi@stis.ac.id',81234567890,41);
INSERT INTO "peserta" VALUES (29,'Adhi',222212918,'adhi@stis.ac.id',81234567890,20);
INSERT INTO "peserta" VALUES (30,'Adhi',222212918,'adhi@stis.ac.id',81234567890,40);
INSERT INTO "peserta" VALUES (32,'Arisha',222212572,'222212572@stis.ac.id',81256772345,20);
INSERT INTO "peserta" VALUES (33,'Arisha',222212572,'222212572@stis.ac.id',81238923829,21);
INSERT INTO "peserta" VALUES (34,'Arisha',222212572,'222212572@stis.ac.id',812423672,17);
INSERT INTO "users" VALUES (222212572,'222212572@stis.ac.id','Arisha','ari123',2);
INSERT INTO "users" VALUES (222212915,'222212915@stis.ac.id','wilfa','wilfa12345',1);
INSERT INTO "users" VALUES (222212917,'adhi@stis.ac.id','Adhi','adhi123',2);
COMMIT;
