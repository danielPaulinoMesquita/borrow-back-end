insert into product (id, days, description, type, "value", customer_id)
values (1,'4','Tesoura','UTENSILIOS','300',1),
       (2,'5','Tesoura','UTENSILIOS','300',1);


select * from product p where p.customer_id = '1';

