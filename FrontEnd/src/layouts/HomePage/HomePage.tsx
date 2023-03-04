import { Carousel } from "./components/Carousel";
import { ExploreTopBooks } from "./components/ExploreTopBooks";
import { Heros } from "./components/Heros";
import { LibraryServices } from "./components/LibraryServices";
// TODO The HomePage component returns a JSX expression that includes four other components, ExploreTopBooks, Carousel, Heros, and LibraryServices. The HomePage component is essentially a composition of these components that create the overall appearance of the home page.
export const HomePage = () => {
    return (
        <>
            <ExploreTopBooks/>
            <Carousel/>
            <Heros/>
            <LibraryServices/>
        </>
    );
}