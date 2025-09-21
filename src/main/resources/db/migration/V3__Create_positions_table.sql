CREATE TABLE positions (
    id UUID PRIMARY KEY,
    quantity INTEGER NOT NULL,
    account_id UUID NOT NULL,
    product_id UUID NOT NULL,
    CONSTRAINT fk_account
        FOREIGN KEY(account_id)
        REFERENCES accounts(id),
    CONSTRAINT fk_product
        FOREIGN KEY(product_id)
        REFERENCES products(id)
);