-- 코드를 입력하세요
SELECT b.mcdp_cd AS '진료과 코드', b.cnt AS '5월예약건수' 
FROM (SELECT mcdp_cd, COUNT(*) AS cnt
    FROM (SELECT * FROM appointment WHERE YEAR(apnt_ymd) = 2022 AND MONTH(apnt_ymd) = 5) AS a
    GROUP BY(a.mcdp_cd)) AS b
ORDER BY b.cnt ASC, b.mcdp_cd ASC;
