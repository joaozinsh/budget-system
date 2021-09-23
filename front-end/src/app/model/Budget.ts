import { BudgetItem } from "./BudgetItem";
import { User } from "./User";

export class Budget {

    public id: number;
    public creationDate: Date;
    public validity: number
    public user: User;
    public clientName: string;
    public clientAddress: string;
    public comments: string;
    public total: number;
    public quantityItems: number;
    public quantityParts: number;
    public daysUntilExpires: number;
    public items: BudgetItem[];
}