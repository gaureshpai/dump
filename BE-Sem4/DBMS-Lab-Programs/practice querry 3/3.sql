select w.pname, l.city from works w, located_in l 
where w.cname = l.cname and l.cname='wipro';