import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filter'
})
export class FilterPipe implements PipeTransform {
    transform(items: any[], searchText: string): any[] {
        if (!items) return [];
        if (!searchText) return items;
        searchText = searchText.toLowerCase();
        return items.filter(it => {
            const name = it.name ? it.name.toLowerCase() : '';
            const location = it.location ? it.location.toLowerCase() : '';
            const type = it.type ? it.type.toLowerCase() : '';
            const features = it.features ? it.features.toLowerCase() : '';
            return name.includes(searchText) ||
                location.includes(searchText) ||
                type.includes(searchText) ||
                features.includes(searchText);
        });
    }
}
