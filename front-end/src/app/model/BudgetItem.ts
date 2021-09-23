import { Budget } from "./Budget";

export class BudgetItem {

    public id: number;
    public name: string;
    public quantityParts: number;
    public unitaryValue: number;
    public percentDiscount: number;
    public subTotal: number;
    public budget: Budget;
}