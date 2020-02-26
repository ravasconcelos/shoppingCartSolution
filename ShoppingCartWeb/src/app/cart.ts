import { CartItem } from './cartItem';

export class Cart {
    id: number;
    name: string;
    status: string;
    items: CartItem[];
}
