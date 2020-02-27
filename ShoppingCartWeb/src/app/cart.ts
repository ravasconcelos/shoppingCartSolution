import { CartItem } from './cartItem';

export class Cart {
    id: number;
    name: string;
    status: string;
    orderId: number;
    items: CartItem[];
}
