#!/bin/sh

# удаляет все файлы старше 14 дня (за исключением 15-го числа месяца).
find db_backup \( -name "*-1[^5].*" -o -name "*-[023]?.*" \) -ctime +14 -delete

pg_dump -s -U admin postgres > /db_backup/cl_schema_$(date "+%Y-%m-%d")
pg_dump -a -U admin postgres > /db_backup/cl_data_$(date "+%Y-%m-%d")